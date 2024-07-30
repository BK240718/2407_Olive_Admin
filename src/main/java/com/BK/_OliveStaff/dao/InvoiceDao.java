package com.BK._OliveStaff.dao;

import com.BK._OliveStaff.dto.Invoice;

import java.util.List;

public interface InvoiceDao {

    List<Invoice> listInvoice(Invoice invoice);

    int totalInvoice();
}
