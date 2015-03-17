/**
 * Created by Jing on 13-12-12.
 */

define(function (require) {
    var $ = require('jquery');

    require('icheck')($);

    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green'
    });

    var base = $('body').data('base');
    
    $('.saveedit').click(function () {
    
        var that = $(this).closest('div');
       
        if(!window.confirm("您确定修改吗?"))
        {
        	return;
        }
        
        var order_payPrice="-1";
        try{
        
        	order_payPrice=document.getElementById("order_payPrice").value;
        }catch(Exception)
        {
        	order_payPrice="-1";
        }
        
        //var collect=document.getElementById("collect").value;
        
        //var sales=document.getElementById("sales").value;
        
        var _remark=document.getElementById("remark").value;
        
        $.ajax({
            url: base + '/manage/order/saveedit',
            type: "POST",
            data: {order_payPrice: order_payPrice, id: that.data('id'),remark:_remark},
            //data: {order_payPrice: order_payPrice, id: that.data('id'),collect:collect,sales:sales},
            success: function (res) {
                if (res.success) {
                    //location.reload();
                	alert("修改成功");
                	location= base + '/manage/order/';
                    
                }
            }
        });
       
    });
    
    
});