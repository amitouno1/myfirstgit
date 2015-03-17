/**
 * Created by Jing on 13-12-12.
 */

define(function (require, exports) {
    var $ = require('jquery');

    require('icheck')($);

    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green'
    });

    var base = $('body').data('base');
    
    
    $('.removeUser').click(function () {
        var that = $(this).closest('div');
        
        if(!window.confirm("您确定删除用户吗?"))
        {
        	return;
        }
        
        $.ajax({
            url: base + '/manage/member/deleteUser',
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