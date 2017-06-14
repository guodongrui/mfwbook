$(document).ready(function() {

	// 默认显示用户书单列表 "在读的书"
	$('.bookList:first').show();
	$('.book-state li:first').addClass('active');

	// 搜索
	$('#search').on('click', function() {
	      var input = $('#search-input').val();
	      var options = $('#sel option:selected').val();
	    	  $.post("/s", {className:"书名",s:input}, function(data) {
		          $('#showBook').html(data[0].bookName);
		      });
	      return true;
	  });
	
	var linkIndex = 0; // 获取点击的书籍状态列表位置
	$('ul.book-state li a').on('click', function() {
		linkIndex = $('ul.book-state li a').index(this);
		$('.book-state li').removeClass('active');
		$(".bookList:visible").hide();
		$(".bookList:eq(" + linkIndex + ")").show();
		$(this).parent().addClass('active');
		return false;
	});
	
	$('#add').on(
			'click',
			function() {
				var nameVal = $('#add-input').val();
				$.get("/addbook", {
					bookName : nameVal,
					pos : linkIndex
				}, function(data) {
					$('tbody:eq(' + linkIndex + ')').append(
							'<tr><td class="text-center">' + data.bookName
									+ '</td><td class="text-center">' + data.bookTypes
									+ '</td><td class="text-center">' + data.author
									+ '</td><td class="text-center">'
									+ '<button class="btn btn-danger btn-sm del">删除</button>'
					                + '</td></tr>');
					$('#add-input').val('');
				});
			});
	
	$('.del').on('click', function() {
		var tr = $(this).parent().parent();
		var bookName = tr.children(':first-child').text();
		tr.remove();
		$.get('/deletebook', {bookName: bookName, pos: linkIndex});
	});
	
});