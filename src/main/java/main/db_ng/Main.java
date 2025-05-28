package main.db_ng;

import db_class.Datas;
import db_class.Headers;
import db_class.Row;
import db_class.Table;
import utils.Utility;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        List<String> values = List.of("Mario", "Rossi", "+39 8884521002");

        Headers headers = new Headers(List.of("Nome", "Cognome", "Phone"));
        List<Datas> datas = new LinkedList<>();

        List<String> columnNames = headers.getColumnName();

        IntStream.range(0, columnNames.size())
                .forEach(i -> datas.add(new Datas(columnNames.get(i), values.get(i))));


        Row row = new Row(1, datas);
        Table table = new Table("Tabella 1" ,headers);
        table.addRow(row);

        Utility.printFormattedJson(table.toJSON());
    }
}