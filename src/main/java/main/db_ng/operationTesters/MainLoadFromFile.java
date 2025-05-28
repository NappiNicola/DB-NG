package main.db_ng.operationTesters;

import db_class.Table;
import operationsIO.IOFile;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Utility;

public class MainLoadFromFile {

    public static void main(String[] args) {

        Table table = new Table();
        JSONObject obj = new JSONObject();

        obj = IOFile.readFromFile("Customers");

        table = Utility.parseJsonToTable(obj);

        Utility.printTableLikeTable(table);

    }

}
