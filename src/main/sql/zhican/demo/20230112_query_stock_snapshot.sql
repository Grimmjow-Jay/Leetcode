select os.serial_number                                                                    as 流水号,
       os.order_number                                                                     as 订单号,
       os.type_desc                                                                        as 操作类型,
       os.sku,
       os.unit_name                                                                        as 单位,
       os.warehouse_name                                                                   as 仓库,
       if(p.chaoma = 0, occupy_log_item.quantity,
          concat(occupy_log_item.quantity, ' (', occupy_log_item.weight, 'KG)'))           as 预占量,
       if(p.chaoma = 0, release_log_item.quantity,
          concat(release_log_item.quantity, ' (', release_log_item.weight, 'KG)'))         as 释放量,
       if(p.chaoma = 0, delivery_log_item.quantity,
          concat(delivery_log_item.quantity, ' (', delivery_log_item.weight, 'KG)'))       as 发货量,
       if(p.chaoma = 0, os.original_usable_quantity,
          concat(os.original_usable_quantity, ' (', os.original_usable_weight, 'KG)'))     as 初始可用,
       if(p.chaoma = 0, os.original_occupied_quantity,
          concat(os.original_occupied_quantity, ' (', os.original_occupied_weight, 'KG)')) as 初始预占,
       if(p.chaoma = 0, os.balance_usable_quantity,
          concat(os.balance_usable_quantity, ' (', os.balance_usable_weight, 'KG)'))       as 结存可用,
       if(p.chaoma = 0, os.balance_occupied_quantity,
          concat(os.balance_occupied_quantity, ' (', os.balance_occupied_weight, 'KG)'))   as 结存预占,
       os.created_time                                                                     as 时间,
       os.occupy_serial_number                                                             as 预占流水号,
       os.release_serial_number                                                            as 释放流水号,
       os.delivery_serial_number                                                           as 发货流水号
from zc_scmpdb.zc_stock_inventory_stock_occupy_snapshot os
         left join zc_scmpdb.zc_product p on os.sku = p.sku_code
         left join zc_scmpdb.zc_stock_inventory_stock_occupy_log occupy_log
                   on os.occupy_serial_number = occupy_log.serial_number
         left join zc_scmpdb.zc_stock_inventory_stock_occupy_log_item occupy_log_item
                   on occupy_log.id = occupy_log_item.occupy_log_id
                       and os.sku = occupy_log_item.sku
                       and os.unit_code = occupy_log_item.unit_code
                       and os.warehouse_code = occupy_log_item.warehouse_code
         left join zc_scmpdb.zc_stock_inventory_stock_occupy_log release_log
                   on os.release_serial_number = release_log.serial_number
         left join zc_scmpdb.zc_stock_inventory_stock_occupy_log_item release_log_item
                   on release_log.id = release_log_item.occupy_log_id
                       and os.sku = release_log_item.sku
                       and os.unit_code = release_log_item.unit_code
                       and os.warehouse_code = release_log_item.warehouse_code
         left join zc_scmpdb.zc_stock_inventory_stock_occupy_log delivery_log
                   on os.delivery_serial_number = delivery_log.serial_number
         left join zc_scmpdb.zc_stock_inventory_stock_occupy_log_item delivery_log_item
                   on delivery_log.id = delivery_log_item.occupy_log_id
                       and os.sku = delivery_log_item.sku
                       and os.unit_code = delivery_log_item.unit_code
                       and os.warehouse_code = delivery_log_item.warehouse_code;