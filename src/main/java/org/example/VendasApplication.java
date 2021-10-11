package org.example;

import org.example.domain.entity.Cliente;
import org.example.domain.entity.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository){
        return args -> {
            System.out.println("Salvando clientes");
            clienteRepository.salvar(new Cliente("Rafael"));
            clienteRepository.salvar(new Cliente("Outro Cliente qualquer"));

            System.out.println("Listando todos os clientes");
            List<Cliente> todosClientes = clienteRepository.obterTodosClientes();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c->{
                c.setNome(c.getNome() + " atualizado");
                clienteRepository.atualizar(c);
            });
            System.out.println("Buscando todos os  clientes atualizados");
            todosClientes = clienteRepository.obterTodosClientes();
            todosClientes.forEach(System.out::println);

            System.out.println("buscando clientes por nome");
            clienteRepository.buscarPorNome("Cli").forEach(System.out::println);

            System.out.println("Deletando clientes");
            clienteRepository.obterTodosClientes().forEach(c->{
                clienteRepository.deletar(c);
            });

            todosClientes = clienteRepository.obterTodosClientes();
            if(todosClientes.isEmpty()){
                System.out.println("Todos os clientes foram deletados");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
