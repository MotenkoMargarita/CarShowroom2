package backend.services;

import backend.dao.ClientDAO;
import backend.entities.Client;

import java.sql.Connection;
import java.util.List;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService(Connection connection) {
        this.clientDAO = new ClientDAO(connection);
    }

    public List<Client> getClients() {
        return clientDAO.getAll();
    }

    public Long addClient(Client client) {
        return clientDAO.insert(client);
    }

    public boolean deleteClient(long id) {
        return clientDAO.delete(id);
    }

    public boolean editClient(Client client) {
        return clientDAO.update(client);
    }
}
