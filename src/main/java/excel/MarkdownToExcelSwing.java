package excel;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MarkdownToExcelSwing {
	private JFrame mainFrame; // 메인프레임
	private JPanel controlPanel = new JPanel();
	private JButton openInputBtn = new JButton("열기");
	private JButton openOutputBtn = new JButton("열기");
	private JLabel inputLabel = new JLabel("입력파일명 : ");
	private JLabel outputLabel = new JLabel("출력파일명 : ");
	private JTextField inputText = new JTextField();
	private JTextField outputText = new JTextField();

	private JButton runBtn = new JButton("실행");

	/*
	 * 최초 프레임창 UI 설정
	 */
	private MarkdownToExcelSwing() {
		mainFrame = new JFrame("MarkdownToExcel");
		mainFrame.setSize(800, 200);
		mainFrame.setLayout(new BorderLayout());

		// 이벤트등록
		openInputBtn.addActionListener(fileActionEvent(inputText, FileDialog.LOAD));
		openOutputBtn.addActionListener(fileActionEvent(outputText, FileDialog.SAVE));
		runBtn.addActionListener(runActionEvent());

		// 메인프레임 종료시 시스템 종료
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.out.println("close!");
				System.exit(0);
			}
		});

		// 패널 할당
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(3, 1));

		controlPanel.add(inputLabel);
		controlPanel.add(inputText);
		controlPanel.add(openInputBtn);
		controlPanel.add(outputLabel);
		controlPanel.add(outputText);
		controlPanel.add(openOutputBtn);
		controlPanel.add(runBtn);

		// 1번판 영역 추가
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	private ActionListener fileActionEvent(JTextField text, int actionMode) {
		return ActionListener -> {
			FileDialog fDialog = new FileDialog(mainFrame, "", actionMode);
			fDialog.setVisible(true);
			text.setText(fDialog.getDirectory() + fDialog.getFile());
		};
	}

	private ActionListener runActionEvent() {

		return ActionListener -> {
			Path inPath = Paths.get(inputText.getText());
			Path outPath = Paths.get(outputText.getText());
			
			// 파일명에 xlsx 확장자가 없는 경우 추
			if (!outputText.getText().matches(".xlsx$")) {
				outPath = Paths.get(outputText.getText() + ".xlsx");
			}
			
			try (OutputStream os = Files.newOutputStream(outPath)) {
				List<String> collect = Files.lines(inPath, StandardCharsets.UTF_8).collect(Collectors.toList());
				collect.remove(1);
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet();
				for (int i = 0; i < collect.size(); i++) {
					String[] rowString = collect.get(i).split("\\|");
					rowString = Arrays.copyOfRange(rowString, 1, rowString.length);	// Markdown 생성시 처음에 생기는 | 부분 삭
					Row row = sheet.createRow(i);
					for (int j = 0; j < rowString.length; j++) {
						Cell cell = row.createCell(j);
						cell.setCellValue(rowString[j]);
					}
				}
				workbook.setSheetName(0, "Sheet1");
				workbook.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};

	}

	public static void main(String[] args) {
		MarkdownToExcelSwing markdownToExcelSwing = new MarkdownToExcelSwing();
	}
}
