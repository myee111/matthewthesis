package contract;

import java.sql.SQLException;
import resallocDB.DBHandler;

public class ContractDB extends Contract{
	/**
	 * @param ContractID
	 * @param salesStatus
	 * @param Ownership
	 * @param Delivery
	 * @param Duration
	 * @param Resources
	 * @param Price
	 * @param Commencedate
	 * @throws SQLException
	 */
	public void commitRecord() throws SQLException {
		DBHandler D1 = new DBHandler();
		D1.setContractID(getContractID());
		D1.setSaleStatus(getsalesStatus());
		D1.setOwnership(getOwnership());
		D1.setDelivery(getDelivery());
		D1.setDuration(getDuration());
		D1.setResources(getResources());
		D1.setPrice(getPrice());
		D1.setCommencedate(getCommencedate());
		D1.opendbConnection();
		D1.committoContracttbl();
		D1.closedbConnection();
		return;
	}
	public void retrieveRecord(String contractID) throws SQLException {
		DBHandler D1 = new DBHandler();
		D1.opendbConnection();
		D1.retrieveRecordfromContracttbl(contractID);
		setContractID(contractID);
		setSaleStatus(D1.isSaleStatus());
		setOwnership(D1.getOwnership());
		setDelivery(D1.isDelivery());
		setDuration(D1.getDuration());
		setResources(D1.getResources());
		setPrice(D1.getPrice());
		setCommencedate(D1.getCommencedate());
		D1.closedbConnection();
		return;
	}
	public void deleteRecord(String contractID) throws SQLException {
		DBHandler D1 = new DBHandler();
		D1.opendbConnection();
		D1.deleteRecordfromContracttbl(contractID);
		D1.closedbConnection();
		return;
	}
}
