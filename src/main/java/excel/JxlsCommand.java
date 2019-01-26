package excel;

import org.jxls.command.CellDataUpdater;
import org.jxls.common.CellData;
import org.jxls.common.CellRef;
import org.jxls.common.Context;

public class JxlsCommand implements CellDataUpdater {
  public void updateCellData(CellData cellData, CellRef targetCell, Context context) {
    String formula = cellData.getFormula();
    if (cellData.isFormulaCell() && (formula.equals("SUM(G38)"))) {
      String resultFormula = String.format("SUM(G38:G%d)", targetCell.getRow());
      cellData.setEvaluationResult(resultFormula);
    }
  }
}
