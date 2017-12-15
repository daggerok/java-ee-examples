package daggerok.rpc.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

  String find(final String key) throws RemoteException;

  void save(final String key, final String value) throws RemoteException;
}
