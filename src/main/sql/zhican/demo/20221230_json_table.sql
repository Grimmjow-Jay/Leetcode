select @@global.group_concat_max_len;


set group_concat_max_len = 102400;


update zc_order.product_price_info ppi_update
    inner join
    (
        select ppi.id,
               concat('[',
                      group_concat(
                              concat('{"warehouseId":', t_warehouse.warehouseId, ',',
                                     '"warehouseCode":"', t_warehouse.warehouseCode, '",',
                                     '"warehouseName":"', t_warehouse.warehouseName, '",',
                                     '"stockSync":', if(ppi.is_weight = 1, '2', '1'), '}'
                                  ) separator ','),
                      ']') as updated_warehouse
        from zc_order.product_price_info ppi
                 JOIN
             JSON_TABLE(ppi.release_warehouse_info_list, '$[*]'
                        COLUMNS (warehouseId bigint PATH '$.warehouseId',
                            warehouseCode varchar(30) PATH '$.warehouseCode',
                            warehouseName varchar(30) PATH '$.warehouseName')
                 ) AS t_warehouse
        where ppi.release_warehouse_info_list is not null
        group by ppi.id
    ) t on ppi_update.id = t.id
set ppi_update.release_warehouse_info_list = t.updated_warehouse
where ppi_update.release_warehouse_info_list is not null;

