package frontend.console.views;

import backend.services.ClientService;

public class ClientView {
    private ClientService clientService;

    public ClientView(ClientService clientService) {
        this.clientService = clientService;
    }

    public void showClients() {
        clientService.getClients().forEach(client -> System.out.println(client.toString()));
    }
}
