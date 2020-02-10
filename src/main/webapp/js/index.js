$(function(){

    // 界面初始化时候，异步ajax方式获取最新商品
    $.ajax({
        url:'/product/getNewProducts',
        success:function (res) {
            if(res.data!=null&& res.data.length!=0){
                // jQuery的方式 将数据循环到界面
                for(var p in res.data){
                    $("#newProduct .tabContent").append("<li><a  target='_blank'> <img src='"+res.data[p].image+"' style='display: block;'> </a> </li>");
                }
            }
        }
    })
})