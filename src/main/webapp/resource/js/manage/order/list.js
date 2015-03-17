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
    
    $('.delete_order').click(function () {
        var that = $(this).closest('div');
        
        if(!window.confirm("您确定删除吗?"))
        {
        	return;
        }
        
        $.ajax({
            url: base + '/manage/order/deleteorder',
            type: "POST",
            data: {id: that.data('id')},
            success: function (res) {
                if (res.success) {
                    location.reload();
                }
            }
        });
    });
    
});