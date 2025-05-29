package main.GUI;

import db_class.Table;
import operationsIO.IOFile;
import org.json.JSONObject;
import utils.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableCreator extends JFrame {
    private JTextField tableNameField;
    private DefaultListModel<String> columnListModel;
    private JTable dataTable;
    private DefaultTableModel dataTableModel;

    public TableCreator() {
        setTitle("Table Creator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel: Table name and columns
        JPanel topPanel = new JPanel(new BorderLayout());

        // Table Name
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Table Name:"));
        tableNameField = new JTextField(20);
        namePanel.add(tableNameField);
        topPanel.add(namePanel, BorderLayout.NORTH);

        // Column panel
        columnListModel = new DefaultListModel<>();
        JList<String> columnList = new JList<>(columnListModel);
        columnList.setVisibleRowCount(5);
        JScrollPane columnScroll = new JScrollPane(columnList);

        JPanel columnPanel = new JPanel(new BorderLayout());
        columnPanel.setBorder(BorderFactory.createTitledBorder("Columns"));
        columnPanel.add(columnScroll, BorderLayout.CENTER);

        JPanel addColPanel = new JPanel();
        JTextField colField = new JTextField(10);
        JButton addColBtn = new JButton("Add Column");
        addColBtn.addActionListener(e -> {
            String colName = colField.getText().trim();
            if (!colName.isEmpty()) {
                columnListModel.addElement(colName);
                colField.setText("");
                refreshTableData();
            }
        });
        addColPanel.add(colField);
        addColPanel.add(addColBtn);
        columnPanel.add(addColPanel, BorderLayout.SOUTH);

        topPanel.add(columnPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel: Data Table
        dataTableModel = new DefaultTableModel();
        dataTable = new JTable(dataTableModel);
        JScrollPane tableScroll = new JScrollPane(dataTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Data Rows"));
        add(tableScroll, BorderLayout.CENTER);

        // Bottom Panel: Buttons
        JPanel bottomPanel = new JPanel();
        JButton addRowBtn = new JButton("Add Row");
        addRowBtn.addActionListener(e -> dataTableModel.addRow(new Object[dataTableModel.getColumnCount()]));
        JButton generateBtn = new JButton("Generate JSON");
        generateBtn.addActionListener(this::generateJSON);
        bottomPanel.add(addRowBtn);
        bottomPanel.add(generateBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void refreshTableData() {
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < columnListModel.size(); i++) {
            columns.add(columnListModel.get(i));
        }

        dataTableModel.setColumnIdentifiers(columns.toArray());
        dataTableModel.setRowCount(0); // clear existing rows
    }

    private void generateJSON(ActionEvent e) {
        String tableName = tableNameField.getText().trim();
        if (tableName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Table name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < columnListModel.size(); i++) {
            columnNames.add(columnListModel.get(i));
        }

        List<Map<String, String>> rows = new ArrayList<>();
        for (int i = 0; i < dataTableModel.getRowCount(); i++) {
            Map<String, String> row = new LinkedHashMap<>();
            for (int j = 0; j < dataTableModel.getColumnCount(); j++) {
                String col = dataTableModel.getColumnName(j);
                Object value = dataTableModel.getValueAt(i, j);
                row.put(col, value == null ? "" : value.toString());
            }
            rows.add(row);
        }

        // JSON creation
        JSONObject json = new JSONObject();
        json.put("tableName", tableName);
        json.put("headers", new JSONObject().put("columnNames", columnNames));

        var jsonRows = new org.json.JSONArray();
        for (int i = 0; i < rows.size(); i++) {
            var rowJson = new JSONObject();
            rowJson.put("idRow", i);

            var params = new org.json.JSONArray();
            for (var entry : rows.get(i).entrySet()) {
                var data = new JSONObject();
                data.put("dataName", entry.getKey());
                data.put("value", entry.getValue());
                params.put(data);
            }

            rowJson.put("params", params);
            jsonRows.put(rowJson);
        }
        json.put("rows", jsonRows);

        // Output to console
        System.out.println(json.toString(4));
        Table table = Utility.parseJsonToTable(json);
        Utility.printTableLikeTable(table);
        IOFile.saveOnFile(table.getTableName(), json);
        JOptionPane.showMessageDialog(this, "Table saved!");
    }

}
