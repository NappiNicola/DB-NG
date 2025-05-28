package db_class;

import org.json.JSONObject;

import java.util.List;

public class Row {

    private int idRow;
    private List<Datas> params;

    public Row(int idRow, List<Datas> params) {
        this.idRow = idRow;
        this.params = params;
    }

    public int getIdRow() {
        return idRow;
    }

    public void setIdRow(int idRow) {
        this.idRow = idRow;
    }

    public List<Datas> getParams() {
        return params;
    }

    public void setParams(List<Datas> params) {
        this.params = params;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("idRow", idRow);
        obj.put("params", params);

        return obj;
    }

}
