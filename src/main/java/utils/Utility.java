package utils;

import db_class.Datas;
import db_class.Headers;
import db_class.Row;
import db_class.Table;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {

    public static void printFormattedJson(JSONObject jsonObject) {
        System.out.println(jsonObject.toString(4));
    }

    public static String inpuData(String msg){
        Scanner scanner = new Scanner(System.in);
        System.out.print(msg + ": ");


        String val = scanner.nextLine().trim();
        return val;
    }

    public static Integer inputInteger(String msg){
        String val = inpuData(msg);
        return Integer.parseInt(val);
    }

    public static Table parseJsonToTable(JSONObject json) {
        String tableName = json.getString("tableName");

        // Headers
        JSONArray colArray = json.getJSONObject("headers").getJSONArray("columnNames");
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < colArray.length(); i++) {
            columnNames.add(colArray.getString(i));
        }
        Headers headers = new Headers(columnNames);

        // Rows
        JSONArray rowsArray = json.getJSONArray("rows");
        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < rowsArray.length(); i++) {
            JSONObject rowObj = rowsArray.getJSONObject(i);
            int idRow = rowObj.getInt("idRow");

            JSONArray paramsArray = rowObj.getJSONArray("params");
            List<Datas> datasList = new ArrayList<>();
            for (int j = 0; j < paramsArray.length(); j++) {
                JSONObject paramObj = paramsArray.getJSONObject(j);
                String dataName = paramObj.getString("dataName");
                String value = paramObj.getString("value");
                datasList.add(new Datas(dataName, value));
            }

            rows.add(new Row(idRow, datasList));
        }

        return new Table(tableName, headers, rows);
    }

    public static void printTableLikeTable(Table table) {
        List<String> headers = table.getHeaders().getColumnName();
        List<Row> rows = table.getRows();

        // Calcola la larghezza massima per ogni colonna
        List<Integer> colWidths = new ArrayList<>();
        for (String header : headers) {
            colWidths.add(header.length());
        }

        for (Row row : rows) {
            List<Datas> dataList = row.getParams();
            for (int i = 0; i < dataList.size(); i++) {
                int len = dataList.get(i).getValue().length();
                if (len > colWidths.get(i)) {
                    colWidths.set(i, len);
                }
            }
        }

        // Genera la riga di separazione
        String separator = generateSeparator(colWidths);

        // Stampa intestazioni
        System.out.println(separator);
        StringBuilder headerRow = new StringBuilder("|");
        for (int i = 0; i < headers.size(); i++) {
            headerRow.append(" ").append(padRight(headers.get(i), colWidths.get(i))).append(" |");
        }
        System.out.println(headerRow);
        System.out.println(separator);

        // Stampa righe
        for (Row row : rows) {
            StringBuilder rowLine = new StringBuilder("|");
            for (int i = 0; i < headers.size(); i++) {
                String value = row.getParams().get(i).getValue();
                rowLine.append(" ").append(padRight(value, colWidths.get(i))).append(" |");
            }
            System.out.println(rowLine);
            System.out.println(separator);  // ✅ Separatore dopo ogni riga
        }
    }

    // Metodo per creare la riga di separazione
    private static String generateSeparator(List<Integer> colWidths) {
        StringBuilder line = new StringBuilder("+");
        for (int width : colWidths) {
            line.append("-".repeat(width + 2)).append("+");
        }
        return line.toString();
    }

    // Metodo di supporto per padding
    private static String padRight(String text, int width) {
        return String.format("%-" + width + "s", text);
    }


}
