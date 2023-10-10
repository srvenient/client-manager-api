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

import java.util.Arrays;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

/**
 * The type Client model manager.
 */
public final class ClientModelManager {

  /**
   * Generate shared key.
   *
   * @param fullName the name
   * @return the new shared key
   */
  public static String generateSharedKey(final @NotNull String fullName) {
    final var nameList = Arrays.asList(fullName.toLowerCase().split(" "));
    Collections.shuffle(nameList);

    if (nameList.size() < 2) {
      throw new IllegalArgumentException("Invalid name length");
    }

    final var builder = new StringBuilder();

    final var first = nameList.get(0);
    final var second = nameList.get(1);

    switch (nameList.size()) {
      case 2 -> builder.append(first.charAt(0))
        .append(second);
      case 3 -> {
        final var third = nameList.get(2);

        builder.append(first.charAt(0))
          .append(second)
          .append(third.charAt(0));
      }
      case 4 -> {
        final var third = nameList.get(2);
        final var four = nameList.get(3);

        builder.append(first.charAt(0))
          .append(second)
          .append(third.charAt(0))
          .append(four.charAt(0));
      }
      default -> throw new IllegalArgumentException("Invalid name length");
    }

    return builder.toString();
  }

  private boolean isExist(final @NotNull String[] names, final @NotNull String name) {
    return Arrays.asList(names).contains(name);
  }
}
