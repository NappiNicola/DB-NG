package db_class;

import org.json.JSONObject;

public class Datas {

    private String dataName;
    private String value;

    public Datas() {

    }

    public Datas(String dataName, String value) {
        this.dataName = dataName;
        this.value = value;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("value", value);
        obj.put("dataName", dataName);

        return obj;
    }
}
