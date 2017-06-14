package com.intalgent.sql;

import com.intalgent.addressbook.domain.Contact;
import com.intalgent.addressbook.domain.Table;
import com.intalgent.addressbook.domain.TableField;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class DatabaseMetaDateApplication {

    private Contact contact;


    private DatabaseMetaData dbMetaData = null;
    private Connection con = null;

    public DatabaseMetaDateApplication(){}

    public DatabaseMetaDateApplication(Contact contact) throws SQLException, ClassNotFoundException {
        this.contact = contact;
        setDatabaseMetaData();
    }


    private void setDatabaseMetaData() throws ClassNotFoundException,SQLException{
        if (dbMetaData == null && contact != null) {
            Class.forName(contact.getDriverClass());
            Properties props =new Properties();
            props.setProperty("user", contact.getUserName());
            props.setProperty("password", contact.getPassword());
            props.setProperty("remarks", "true"); //设置可以获取remarks信息
            props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
            con = DriverManager.getConnection(contact.getJdbcUrl(), props);
            dbMetaData = con.getMetaData();
        }
    }


    /**
     * 获得数据库的一些相关信息
     */
    public void getDataBaseInformations() {
        try {
            System.out.println("数据库已知的用户: "+ dbMetaData.getUserName());
            System.out.println("数据库的系统函数的逗号分隔列表: "+ dbMetaData.getSystemFunctions());
            System.out.println("数据库的时间和日期函数的逗号分隔列表: "+ dbMetaData.getTimeDateFunctions());
            System.out.println("数据库的字符串函数的逗号分隔列表: "+ dbMetaData.getStringFunctions());
            System.out.println("数据库供应商用于 'schema' 的首选术语: "+ dbMetaData.getSchemaTerm());
            System.out.println("数据库URL: " + dbMetaData.getURL());
            System.out.println("是否允许只读:" + dbMetaData.isReadOnly());
            System.out.println("数据库的产品名称:" + dbMetaData.getDatabaseProductName());
            System.out.println("数据库的版本:" + dbMetaData.getDatabaseProductVersion());
            System.out.println("驱动程序的名称:" + dbMetaData.getDriverName());
            System.out.println("驱动程序的版本:" + dbMetaData.getDriverVersion());


            System.out.println();
            System.out.println("数据库中使用的表类型");
            ResultSet rs = dbMetaData.getTableTypes();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            rs.close();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得该用户下面的所有表
     */
    public List<Table> getAllTableList(String schemaName) {
        List<Table> list = new ArrayList<Table>();
        try {
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
            String[] types = { "TABLE" };
            ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
            Table table;
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME"); 	//表名
                String tableType = rs.getString("TABLE_TYPE"); 	//表类型
                String remarks = rs.getString("REMARKS"); 		//表备注
                table = new Table(tableName);
                table.setComment(remarks);
                list.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获得该用户下面的所有视图
     */
    public void getAllViewList(String schemaName) {
        try{
            String[] types = { "VIEW" };
            ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
            while (rs.next()){
                String viewName = rs.getString("TABLE_NAME"); //视图名
                String viewType = rs.getString("TABLE_TYPE"); //视图类型
                String remarks = rs.getString("REMARKS");		//视图备注
                System.out.println(viewName + "-" + viewType + "-" + remarks);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库中所有方案名称
     */
    public void getAllSchemas(){
        try{
            ResultSet rs = dbMetaData.getSchemas();
            while (rs.next()){
                String tableSchem = rs.getString("TABLE_SCHEM");
                System.out.println(tableSchem);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * 获得表或视图中的所有列信息
     */
    public List<TableField> getTableColumns(String schemaName, String tableName) {
        List<TableField> list = new ArrayList<TableField>();

        try{
            ResultSet rs = dbMetaData.getColumns(null, schemaName, tableName, "%");
            while (rs.next()){
                String tableCat = rs.getString("TABLE_CAT");//表目录（可能为空）
                String tableSchemaName = rs.getString("TABLE_SCHEM");//表的架构（可能为空）
                String columnName = rs.getString("COLUMN_NAME");//列名
                int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
                String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types类型   名称
                int columnSize = rs.getInt("COLUMN_SIZE");//列大小
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");//小数位数
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");//基数（通常是10或2）
                int nullAble = rs.getInt("NULLABLE");//是否允许为null
                String remarks = rs.getString("REMARKS");//列描述
                String columnDef = rs.getString("COLUMN_DEF");//默认值
                int sqlDataType = rs.getInt("SQL_DATA_TYPE");//sql数据类型
                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");   //SQL日期时间分?
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");   //char类型的列中的最大字节数
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");  //表中列的索引（从1开始）
                /**
                 * ISO规则用来确定某一列的为空性。
                 * 是---如果该参数可以包括空值;
                 * 无---如果参数不能包含空值
                 * 空字符串---如果参数为空性是未知的
                 */
                String isNullAble = rs.getString("IS_NULLABLE");

                /**
                 * 指示此列是否是自动递增
                 * 是---如果该列是自动递增
                 * 无---如果不是自动递增列
                 * 空字串---如果不能确定它是否
                 * 列是自动递增的参数是未知
                 */
                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
                TableField tablefield = new TableField(tableCat,tableSchemaName,tableName,columnName,dataType,dataTypeName,columnSize,decimalDigits,
                        numPrecRadix,nullAble,remarks,columnDef,sqlDataType,sqlDatetimeSub,charOctetLength,ordinalPosition,isNullAble,isAutoincrement);
                list.add(tablefield);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获得一个表的索引信息
     */
    public void getIndexInfo(String schemaName, Table table) {
        try{
            Map<String ,List<String>> keyMap = new HashMap<String, List<String>>();
            ResultSet rs = dbMetaData.getIndexInfo(null, schemaName, table.getTableName(), false, false);
            while (rs.next()){
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");//非唯一索引(Can index values be non-unique. false when TYPE is  tableIndexStatistic   )
                String indexQualifier = rs.getString("INDEX_QUALIFIER");//索引目录（可能为空）
                String indexName = rs.getString("INDEX_NAME");//索引的名称
                short type = rs.getShort("TYPE");//索引类型
                String typeStr = "";
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");//在索引列顺序号
                String columnName = rs.getString("COLUMN_NAME");//列名
                String ascOrDesc = rs.getString("ASC_OR_DESC");//列排序顺序:升序还是降序
                int cardinality = rs.getInt("CARDINALITY");   //基数
                switch ( type){
                    case 0 : typeStr = "没有索引"; break;
                    case 1 : typeStr = "聚集索引"; break;
                    case 2 : typeStr = "哈希表索引"; break;
                    case 3 : typeStr = "其他索引"; break;
                }
                List<String> list = keyMap.get(indexName);
                if(list == null){
                    list = new ArrayList<String>();
                    keyMap.put(indexName, list);
                }
                list.add(columnName);
                System.out.println("索引信息:" + nonUnique + "-" + indexQualifier + "-" + indexName + "-" + typeStr + "-" + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);

            }
            table.setKeyMap(keyMap);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * 获得一个表的主键信息
     */
    public void getAllPrimaryKeys(String schemaName, Table table) {
        try{
            ResultSet rs = dbMetaData.getPrimaryKeys(null, schemaName, table.getTableName());
            while (rs.next()){
                String columnName = rs.getString("COLUMN_NAME");//列名
                short keySeq = rs.getShort("KEY_SEQ");//序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
                String pkName = rs.getString("PK_NAME"); //主键名称
                table.addPrimaryList(columnName);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * 获得一个表的外键信息
     */
    public void getAllExportedKeys(String schemaName, String tableName) {

        try{
            ResultSet rs = dbMetaData.getExportedKeys(null, schemaName, tableName);
            while (rs.next()){
                String pkTableCat = rs.getString("PKTABLE_CAT");//主键表的目录（可能为空）
                String pkTableSchem = rs.getString("PKTABLE_SCHEM");//主键表的架构（可能为空）
                String pkTableName = rs.getString("PKTABLE_NAME");//主键表名
                String pkColumnName = rs.getString("PKCOLUMN_NAME");//主键列名
                String fkTableCat = rs.getString("FKTABLE_CAT");//外键的表的目录（可能为空）出口（可能为null）
                String fkTableSchem = rs.getString("FKTABLE_SCHEM");//外键表的架构（可能为空）出口（可能为空）
                String fkTableName = rs.getString("FKTABLE_NAME");//外键表名
                String fkColumnName = rs.getString("FKCOLUMN_NAME"); //外键列名
                short keySeq = rs.getShort("KEY_SEQ");//序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。

                /**
                 * hat happens to foreign key when primary is updated:
                 * importedNoAction - do not allow update of primary key if it has been imported
                 * importedKeyCascade - change imported key to agree with primary key update
                 * importedKeySetNull - change imported key to NULL if its primary key has been updated
                 * importedKeySetDefault - change imported key to default values if its primary key has been updated
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)
                 */
                short updateRule = rs.getShort("UPDATE_RULE");

                /**
                 * What happens to the foreign key when primary is deleted.
                 * importedKeyNoAction - do not allow delete of primary key if it has been imported
                 * importedKeyCascade - delete rows that import a deleted key
                 * importedKeySetNull - change imported key to NULL if its primary key has been deleted
                 * importedKeyRestrict - same as importedKeyNoAction (for ODBC 2.x compatibility)
                 * importedKeySetDefault - change imported key to default if its primary key has been deleted
                 */
                short delRule = rs.getShort("DELETE_RULE");
                String fkName = rs.getString("FK_NAME");//外键的名称（可能为空）
                String pkName = rs.getString("PK_NAME");//主键的名称（可能为空）

                /**
                 * can the evaluation of foreign key constraints be deferred until commit
                 * importedKeyInitiallyDeferred - see SQL92 for definition
                 * importedKeyInitiallyImmediate - see SQL92 for definition
                 * importedKeyNotDeferrable - see SQL92 for definition
                 */
                short deferRability = rs.getShort("DEFERRABILITY");

                System.out.println("外键信息：" + pkTableCat + "-" + pkTableSchem + "-" + pkTableName + "-" + pkColumnName + "-"
                        + fkTableCat + "-" + fkTableSchem + "-" + fkTableName + "-" + fkColumnName + "-" + keySeq + "-"
                        + updateRule + "-" + delRule + "-" + fkName + "-" + pkName + "-" + deferRability);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void colseCon() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
/*        DatabaseMetaDateApplication metaData = new DatabaseMetaDateApplication();
//		metaData.getDataBaseInformations();
        metaData.getAllTableList(null); //表信息
//		metaData.getAllViewList(null);  //视图信息
        metaData.getAllSchemas();
//		metaData.getTableColumns(null, "zsc_admin");
//		metaData.getIndexInfo(null, "zsc_admin");
//		metaData.getAllPrimaryKeys(null, "zsc_admin");
//        metaData.getAllExportedKeys(null, "zsc_admin");
        metaData.colseCon();*/


    }


}