/**
 * 댓글 수정 창 출력
 */

document.querySelectorAll('.modify').forEach(btn => {

    btn.addEventListener('click', function(e) {

        e.preventDefault();

        const article = this.closest('article');
        const cno = article.querySelector('.cno').value;
        const contentTag = article.querySelector('.content');
        const oldContent = contentTag.innerText;
		
		// 수정 버튼 클릭시 출력되는 html
		contentTag.innerHTML =
		    '<form action="' + path + '/comment/modify.do" method="post" class="commentModifyForm">' +
		        '<input type="hidden" name="cno" value="' + cno + '">' +
		        '<input type="hidden" name="parent" value="' + parent + '">' +
		        '<input type="hidden" name="groupName" value="' + groupName + '">' +
		        '<input type="hidden" name="cate" value="' + cate + '">' +
		        '<input type="hidden" name="page" value="' + page + '">' +
		        '<textarea name="content">' + oldContent + '</textarea>' +
		        '<div class="modifyBtns">' +
		            '<input type="submit" value="수정완료" class="btn btnComplete">' +
		            '<button type="button" class="btn btnCancel">취소</button>' +
		        '</div>' +
		    '</form>';
		
		// 취소버튼 동작
		const cancelBtn = contentTag.querySelector('.btnCancel');
			
		cancelBtn.addEventListener('click', function() {
				    contentTag.innerHTML = oldContent;
				});
    });
});

