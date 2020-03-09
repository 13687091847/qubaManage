
function showLoad(msg) {
	return layer.load(2, {
		shade: [0.5, 'gray'], //0.5透明度的灰色背景
	    content: msg,
	    time: 10*1000,
	    success: function (layero) {
	        layero.find('.layui-layer-content').css({
	            'padding-top': '39px',
	            'width': '60px'
	        });
	    }
	});
}
function closeLoad(index) {
	layer.close(index);

}
function showSuccess(msg) {
	layer.msg(msg, {
		time : 1000,
		offset : 'auto'
	});
}