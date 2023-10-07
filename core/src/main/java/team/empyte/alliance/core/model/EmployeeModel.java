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
package team.empyte.alliance.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "sharedKey", length = 60)
  private String sharedKey;

  @Column(name = "businessId", length = 60, unique = true)
  private String businessId;

  @Column(name = "email", length = 60, nullable = false, unique = true)
  private String email;

  @Column(name = "phone", nullable = false)
  private int phone;

  @Column(name = "dataAdded", length = 60, nullable = false)
  private String dateAdded;

  public EmployeeModel() {
  }

  public EmployeeModel(Long id, String businessId, String email, int phone, String dateAdded) {
    this.id = id;
    this.businessId = businessId;
    this.email = email;
    this.phone = phone;
    this.dateAdded = dateAdded;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getSharedKey() {
    return this.sharedKey;
  }

  public void setSharedKey(final String sharedKey) {
    this.sharedKey = sharedKey;
  }

  public String getBusinessId() {
    return this.businessId;
  }

  public void setBusinessId(final String businessId) {
    this.businessId = businessId;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public int getPhone() {
    return this.phone;
  }

  public void setPhone(final int phone) {
    this.phone = phone;
  }

  public String getDateAdded() {
    return this.dateAdded;
  }

  public void setDateAdded(final String dateAdded) {
    this.dateAdded = dateAdded;
  }
}
