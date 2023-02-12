package org.example;

import models.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibemateSessionUnils;

import java.util.List;
import java.util.Scanner;

public class RoleOperation {

    public static void crudOper() {
        var in = new Scanner(System.in);
        String crudOperation = "";

        do {


            if (!crudOperation.equals("")) {
                switch (crudOperation) {
                    case "C":
                        createRole();
                        break;
                    case "R":
                        getRoles();
                        break;
                    case "U":
                        updateRole();
                        break;
                    case "D":
                        deleteRole();
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }

            System.out.println("[C]-Create Role");
            System.out.println("[R]-Read all Roles");
            System.out.println("[U]-Update Role");
            System.out.println("[D]-Delete Role");
            System.out.println("[0]-Exit");
            crudOperation = in.nextLine();

        } while (!crudOperation.equals("0"));
    }

    private static void createRole() {
        System.out.println("Enter Role name:");
        var in = new Scanner(System.in);
        String name = in.nextLine();

        Session context = HibemateSessionUnils.getSessionFactory().openSession();
        Transaction transaction = context.beginTransaction();

        Role role = new Role();
        System.out.println(name);
        role.setName(name);
        context.save(role);

        transaction.commit();
        context.close();
    }

    private static void deleteRole() {
        System.out.println("Enter Role id to delete:");
        var in = new Scanner(System.in);
        String id_string = in.nextLine();
        int id = Integer.parseInt(id_string);

        Session context = HibemateSessionUnils.getSessionFactory().openSession();
        Transaction transaction = context.beginTransaction();

        Role role = context.find(Role.class, id);
        context.delete(role);

        transaction.commit();
        context.close();
    }

    private static void updateRole() {
        System.out.println("Enter new Role name:");
        var in = new Scanner(System.in);
        String name = in.nextLine();

        System.out.println("Enter Role id:");
        String id_string = in.nextLine();
        int id = Integer.parseInt(id_string);

        Session context = HibemateSessionUnils.getSessionFactory().openSession();
        Transaction transaction = context.beginTransaction();

        Role role = context.find(Role.class, id);
        role.setName(name);
        context.update(role);

        transaction.commit();
        context.close();
    }

    private static List<Role> getRoles() {
        Session context = HibemateSessionUnils.getSessionFactory().openSession();
        Transaction transaction = context.beginTransaction();

        String hql = "FROM Role";
        Query query = context.createQuery(hql);
        List<Role> roles = query.list();

        roles.forEach(role -> System.out.println(role.toString()));

        transaction.commit();
        context.close();
        return roles;
    }

}
