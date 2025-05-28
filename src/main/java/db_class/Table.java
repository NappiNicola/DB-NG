package db_class;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table implements Serializable {

    private String tableName;
    private Headers headers;
    private List<Row> rows;

    public Table() {

    }

    public Table(String tableName) {
        this.tableName = tableName;
        rows = new ArrayList<>();
    }

    public Table(String tableName, Headers headers) {
        this.tableName = tableName;
        this.headers = headers;
        this.rows = new LinkedList<>();
    }

    public Table(String tableName, Headers headers, List<Row> rows) {
        this.tableName = tableName;
        this.headers = headers;
        this.rows = rows;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("tableName", tableName);
        json.put("headers", headers.toJSON());
        json.put("rows", rows);

        return json;
    }

}
