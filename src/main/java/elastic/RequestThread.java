package elastic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class RequestThread {
    public static void main(String[] args) {
//        executeCnc();
//        executeCncDev();

        executeTiket();


    }

    private static void executeTiket() {
        executeRun("https://festa.io/events/297/register/tickets",1000);
    }

    private static void executeCnc(){
        Runnable s = ()->{
            executeRun("http://172.19.15.60:18080/cnc/admin/menuManage.do",1000, "menu");

        };
        Runnable s2 = ()->{
            executeRun("http://172.19.15.60:18080/cnc/admin/userManage.do", 2000, "user");
        };

        Runnable s3 = ()->{
            executeRun("http://172.19.15.60:18080/cnc/admin2/userManage.do", 1000, "error");
        };

        new Thread(s).start();
        new Thread(s).start();
        new Thread(s2).start();
        new Thread(s2).start();

//        new Thread(s3).start();
    }

    private static void executeCncDev(){
        Runnable r1 = ()->{
            executeRun("http://213.175.91.152:18080/cnc/mainService.do#/page/111",10000, "조회");

        };
        Runnable r2 = ()->{
            executeRun("http://213.175.91.152:18080/cnc/adminMainService.do#/page/111", 15000, "접수");
        };

        Runnable r3 = ()->{
            executeRun("http://213.175.91.152:18080/cnc/abc", 1000, "에러");
        };

        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r1).start();
        new Thread(r2).start();
//        new Thread(r3).start();

    }



    private static void executeRun(String urlPath, int sleepTime, String name) {
        try {
            while (true) {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                in.lines().forEach(System.out::println);
                System.out.println("================================" + name);
                Thread.sleep(sleepTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void executeRun(String urlPath, int sleepTime){
        try {
            while (true) {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

                Iterator<String> iterator = in.lines().iterator();

                while (iterator.hasNext()) {
                    String next = iterator.next();
                    System.out.println("next = " + next);
//                    if (next.contains("Tickets")) {
//                        System.out.println(next);
//                    }

                }


                Thread.sleep(sleepTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
