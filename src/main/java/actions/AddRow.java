package actions;

import db_class.Datas;
import db_class.Row;
import db_class.Table;
import utils.Utility;

import java.util.LinkedList;
import java.util.List;

public class AddRow{

    private int id;

    public AddRow(){

    }

    public Table addRow(Table table){

        List<Row> list = table.getRows();
        if(list != null && list.size() > 0){
            id = list.getLast().getIdRow();
        } else {
            id = 0;
        }

        List<String> columns = table.getHeaders().getColumnName();
        List<Row> rows = table.getRows();
        List<Datas> dataList = new LinkedList<>();

        insertData(dataList, columns);
        createRow(rows, dataList);

        return table;
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

}
