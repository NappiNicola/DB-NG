package main.db_ng.operationTesters;

import actions.AddRow;
import db_class.Table;
import operationsIO.IOFile;
import org.json.JSONObject;
import utils.Utility;

public class MainAddRow {

    public static void main(String[] args) {

        Table table;
        JSONObject obj;
        AddRow addRow = new AddRow();

        String tableName = System.getProperty("table");
        obj = IOFile.readFromFile(tableName);

        table = Utility.parseJsonToTable(obj);

        Utility.printTableLikeTable(table);

        addRow.addRow(table);
        Utility.printTableLikeTable(table);

        IOFile.saveOnFile(table.getTableName(), table.toJSON());


    }

}
