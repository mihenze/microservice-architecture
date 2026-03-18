package com.mihenze.mscurse.dtocommon.rest.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Валюта", enumAsRef = true, name = "CurrencyEnum")
public enum Currency {
    USD, EUR, RUB
}
