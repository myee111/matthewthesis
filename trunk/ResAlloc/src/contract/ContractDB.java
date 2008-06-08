package contract;

import java.sql.SQLException;
import resallocDB.DBHandler;

public class ContractDB {
	public void commitRecord(String ContractID,
							 boolean salesStatus,
							 int Ownership,
							 boolean Delivery,
							 int Duration,
							 int Resources,
							 int Price,
							 long Commencedate) throws SQLException {
		DBHandler D1 = new DBHandler();
		D1.opendbConnection();
		D1.committoContracttbl(ContractID,
							   salesStatus,
							   Ownership,
							   Delivery,
							   Duration,
							   Resources,
							   Price,
							   Commencedate);
		D1.closedbConnection();
		return;
	}
	public void retrieveRecord(String contractID) throws SQLException {
		DBHandler D1 = new DBHandler();
		D1.opendbConnection();
		D1.retrieveRecordfromContracttbl(contractID);
		D1.closedbConnection();
		return;
	}
	public void deleteRecord() {
		return;
	}
}
