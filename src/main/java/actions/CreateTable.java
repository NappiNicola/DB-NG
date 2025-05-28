package actions;

import db_class.Datas;
import db_class.Headers;
import db_class.Row;
import db_class.Table;
import utils.Utility;
import java.util.LinkedList;
import java.util.List;

public class CreateTable {

    private static int id = 0;

    private Table table = null;

    public CreateTable() {
        makeTable();
    }

    private void makeTable() {
        String tableName;
        List<String> columns = new LinkedList<>();
        Headers headers = new Headers();
        List<Row> rows = new LinkedList<>();

        tableName = Utility.inpuData("Insert table name");

        insertHeader(headers, columns);
        headers.setColumnName(columns);

        String choise = Utility.inpuData("Insert data now?");
        if(choise.equalsIgnoreCase("no")
                || choise.equalsIgnoreCase("n"))
        {
            table = createTable(tableName, headers, null);
//            Utility.printFormattedJson(table.toJSON());
            return;
        }

        while (true){
            List<Datas> dataList = new LinkedList<>();
            insertData(dataList, columns);
            createRow(rows, dataList);

            String next = Utility.inpuData("Do you want to continue with another row?");
            if(next.equalsIgnoreCase("no") || next.equalsIgnoreCase("n")){
                break;
            }
        }

        table = createTable(tableName, headers, rows);

//        Utility.printFormattedJson(table.toJSON());
    }

    public Table makeTableWithHeaders(Table t) {

        List<String> columns = t.getHeaders().getColumnName();
        List<Row> rows = new LinkedList<>();

        while (true){
            List<Datas> dataList = new LinkedList<>();
            insertData(dataList, columns);
            createRow(rows, dataList);

            String next = Utility.inpuData("Do you want to continue with another row?");
            if(next.equalsIgnoreCase("no") || next.equalsIgnoreCase("n")){
                break;
            }
        }

        Table newTable = t;

        newTable = createTable(t.getTableName(), t.getHeaders(), rows);

//        Utility.printFormattedJson(t.toJSON());

        return newTable;
    }

    /*****************************************************************/
    /*                    CREATORI RIGHE E DATI                      */
    /*****************************************************************/

    private void insertHeader(Headers headers, List<String> columns){
        while(true){
            String input = Utility.inpuData("Enter the column name or type <-.-> for stop it");
            if(input.equals("-.-"))
                break;

            columns.add(input);
        }
        headers.setColumnName(columns);
    }

    private void insertData(List<Datas> dataList, List<String> columns){
        for (String column : columns) {
            Datas data = new Datas();
            data.setDataName(column);
            String value = Utility.inpuData("Enter the value for column " + column);
            data.setValue(value);

            dataList.add(data);
        }
    }

    private void createRow(List<Row> rows, List<Datas> dataList){

//        int rowCount = Utility.inputInteger("Enter the Id of rows");
        int rowCount = id++;
        Row row = new Row(rowCount, dataList);

        rows.add(row);
    }

    private Table createTable(String tableName, Headers headers, List<Row> rows){
        Table table;

        if(rows == null){
            table = new Table(tableName, headers);
        } else {
            table = new Table(tableName, headers, rows);
        }

        return table;
    }

    /*****************************************************************/
    /*                        GETTER & SETTER                        */
    /*****************************************************************/

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
