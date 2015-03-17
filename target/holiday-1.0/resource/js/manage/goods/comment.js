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


    $('.reply').click(function () {
    	 var that = $(this).closest('div');
    	 
         if(!window.confirm("您确定回复吗?"))
         {
         	return;
         }
         var replyContent=document.getElementById(that.data('id')+"replyContent").value;
         
         $.ajax({
             url: base + '/manage/goods/replyComment',
             type: "POST",
             data: {id: that.data('id'),replyContent:replyContent},
             success: function (res) {
                 if (res.success) {

                     
                 }
             }
         });
         alert("回复成功");
         //location.reload();
    });

    

});