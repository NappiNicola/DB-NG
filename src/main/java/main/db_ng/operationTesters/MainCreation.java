package main.db_ng.operationTesters;

import actions.CreateTable;
import db_class.Table;
import operationsIO.IOFile;
import utils.Utility;

public class MainCreation {

    public static void main(String[] args) {
        Table table = new Table();
        CreateTable ct = new CreateTable();
        table = ct.getTable();
        Utility.printFormattedJson(table.toJSON());

        IOFile.saveOnFile(table.getTableName(), table.toJSON());

//        table = ct.makeTableWithHeaders(table);
//        Utility.printFormattedJson(table.toJSON());

        IOFile.saveOnFile(table.getTableName(), table.toJSON());

    }

}
