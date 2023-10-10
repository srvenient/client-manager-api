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
package team.empyte.alliance.core.client.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class ClientModel {

  @GeneratedValue(strategy = GenerationType.UUID)
  @Id
  private UUID id;

  @Column
  private String sharedKey;
  @Column
  private String businessId;

  @Column(nullable = false)
  private String phone;
  @Column(nullable = false)
  private String email;

  @Column
  private String dataAdded;

  public ClientModel() {
  }

  public ClientModel(
    final @NotNull UUID id,
    final @NotNull String sharedKey,
    final @NotNull String businessId,
    final @NotNull String phone,
    final @NotNull String email,
    final @NotNull String dataAdded
  ) {
    this.id = id;
    this.sharedKey = sharedKey;
    this.businessId = businessId;
    this.phone = phone;
    this.email = email;
    this.dataAdded = dataAdded;
  }

  public void setId(final @NotNull UUID id) {
    this.id = id;
  }

  public @Nullable UUID getId() {
    return this.id;
  }

  public @Nullable String getSharedKey() {
    return this.sharedKey;
  }

  public void setSharedKey(final @NotNull String sharedKey) {
    this.sharedKey = sharedKey;
  }

  public @NotNull String getBusinessId() {
    return this.businessId;
  }

  public void setBusinessId(final @NotNull String businessId) {
    this.businessId = businessId;
  }

  public @NotNull String getPhone() {
    return this.phone;
  }

  public void setPhone(final @NotNull String phone) {
    this.phone = phone;
  }

  public @NotNull String getEmail() {
    return this.email;
  }

  public void setEmail(final @NotNull String email) {
    this.email = email;
  }

  public @Nullable String getDataAdded() {
    return dataAdded;
  }

  public void setDataAdded(final @NotNull String dateAdded) {
    this.dataAdded = dateAdded;
  }
}
