package com.simp.service.shared.domain.model;

import java.util.List;

public interface Authorization {
    Account account();

    List<String> roles();
}
