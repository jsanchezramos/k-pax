package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Realm;

public interface RealmDao {
	
	public List<Realm> getAllRealms(Realm realm);
	public Realm getRealm(String alias);
	public Realm getRealmByIden(String iden);
	public List<Realm> getUserRealm(String userName);
	public Realm getRealmAlias(String userName, String alias);
	
}
