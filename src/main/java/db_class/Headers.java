package db_class;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Headers {

    private List<String> columnName;

    public Headers() {
        columnName = new ArrayList<String>();
    }

    public Headers(List<String> columnName) {
        this.columnName = columnName;
    }

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("columnNames", columnName);
        return obj;
    }

}
