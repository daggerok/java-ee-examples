package daggerok.services;

import daggerok.domain.User;
import daggerok.domain.UserList;
import lombok.val;

import java.util.UUID;

import static java.util.Collections.singletonList;

public class UserFactoryImpl implements UserFactory {

  @Override
  public UserList getUsers() {

    val users = singletonList(
        new User().setId(UUID.randomUUID().toString())
                  .setFirstName("Maksim")
                  .setLastName("Kostromin"));

    return new UserList().setUsers(users);
  }
}
