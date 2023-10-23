/*
 * This file is part of storage, licensed under the MIT License
 *
 * Copyright (c) 2023 FenixTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package team.empyte.alliance.core.controller;

import jakarta.annotation.Nullable;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.empyte.alliance.core.model.ClientModel;
import team.empyte.alliance.core.repository.ClientRepository;
import team.empyte.alliance.core.util.StringUtil;

/**
 * The type Client controller.
 */
@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClientController {

  @Autowired
  private ClientRepository clientRepository;

  /**
   * Gets all clients.
   *
   * @return the all clients
   */
  @GetMapping("/clients")
  public @Nullable List<ClientModel> getAllClients() {
    return this.clientRepository.findAll();
  }

  /**
   * Gets clients by shared key.
   *
   * @param sharedKey the shared key
   * @return the clients by shared key
   */
  @GetMapping("/clients/search/{sharedKey}")
  public @Nullable ResponseEntity<List<ClientModel>> getClientsBySharedKey(final @NotNull @PathVariable String sharedKey) {
    final var clients = new ArrayList<ClientModel>();
    for (final var client : this.clientRepository.findAll()) {
      final var sk = client.getSharedKey();
      if (sk == null) {
        throw new NullPointerException("Shared key is null");
      }

      if (sk.equals(sharedKey)) {
        clients.add(client);
      }
    }

    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  @GetMapping(
    value = "/clients/search/advanced",
    params = {"businessId", "phone", "email"}
  )
  public @Nullable ResponseEntity<List<ClientModel>> getClientsByAdvancedSearch(
    final @NotNull @RequestParam(required = false) String businessId,
    final @NotNull @RequestParam(required = false) String phone,
    final @NotNull @RequestParam(required = false) String email
  ) {
    final var clients = new ArrayList<ClientModel>();
    for (final var client : this.clientRepository.findAll()) {
      if (URLDecoder.decode(businessId, StandardCharsets.UTF_8).equals(client.getBusinessId())
        && URLDecoder.decode(phone, StandardCharsets.UTF_8).equals(client.getPhone())
        && URLDecoder.decode(email, StandardCharsets.UTF_8).equals(client.getEmail())) {
        clients.add(client);
      }
    }

    if (clients.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(clients, HttpStatus.OK);
    }
  }


  /**
   * Create client model.
   *
   * @param clientModel the client model
   * @return the client model
   */
  @PostMapping("/clients/register")
  public @Nullable ClientModel registerClient(final @NotNull @RequestBody ClientModel clientModel) {
    if (clientModel.getId() == null) {
      clientModel.setId(UUID.randomUUID());
    }

    if (clientModel.getSharedKey() == null) {
      clientModel.setSharedKey(StringUtil.generateSharedKey(clientModel.getBusinessId()));
    }

    if (clientModel.getDataAdded() == null) {
      final var format = new SimpleDateFormat("dd/MM/yyyy");
      final var formatted = format.format(new Date());

      clientModel.setDataAdded(formatted);
    }

    return this.clientRepository.save(clientModel);
  }
}
