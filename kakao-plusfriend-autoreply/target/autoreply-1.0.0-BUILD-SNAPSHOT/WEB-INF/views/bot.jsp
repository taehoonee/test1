
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>루에게 공부시키는 장소</title>
<link rel="icon" href="/autoreply/resources/images/favicon.png" type="image/png">
<link href="/autoreply/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/autoreply/resources/bootstrap/css/cover.css" rel="stylesheet">
<link href="/autoreply/resources/css/lu.css" rel="stylesheet">


</head>
<body>
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">루에게 공부시키는 장소</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li class="active"><a href="#">Home</a></li>
								<!-- 
								<li><a href="#">Features</a></li>
								<li><a href="#">Contact</a></li>
								 -->
							</ul>
						</nav>
					</div>
				</div>
				<div class="inner cover">
					<img alt="루" src="/autoreply/resources/images/lu.png" class="col-xs-12"/>
					<h1 class="cover-heading">루에게 공부시켜주세요!</h1>
					<p class="lead">아직도 루가 모르는 언어가 잔득! 루에게 말을 알려주세요 ㅠㅠ  <code>http://pf.kakao.com/_rxmxmjxl</code>을 통해서 루와 플러스친구도 될 수 있습니다~</p>
					<p class="lead">
						<a href="#" class="btn btn-lg btn-default" data-toggle="modal" data-target="#KnowledgeModal">알려주기</a>
					</p>
				</div>
				<div class="mastfoot">
					<div class="inner">
						<p>Cover template for <a href="http://getbootstrap.com">Bootstrap</a>, by <a href="https://twitter.com/mdo">@mdo</a>.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="KnowledgeModal" tabindex="-1" role="dialog" aria-labelledby="KnowledgeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="KnowledgeModalLabel">루의 지식공간</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<!-- 텍스트 -->
						<div class="form-group">
							<label for="txt_content" class="col-sm-2 control-label">텍스트</label>
							<!-- <p class="lead">실제로 루가 말할 내용을 박스에 작성하신 후, 저장을 눌러주세요.</p> -->
							<div class="col-sm-7">
								<input type="text" class="form-control" id="txt_content" name="txt_content" placeholder="예)오호~ 어서오너라!">
							</div>
							<div class="col-sm-3">
								<button type="button" class="btn btn-success col-xs-12" id="txt_content_btn">텍스트 저장</button>
							</div>
						</div>

						
						<!-- 필터링 -->
						<div class="form-group">
							<label for="reg_content" class="col-sm-2 control-label">필터링</label>
							<!-- <p class="lead">사용자가 말한 내용에서 필터링할 내용을 박스에 작성해주세요. 해당 필터링에 대한 텍스트를 루가 대답해줍니다.</p> -->
							<div class="col-sm-7">
								<input type="text" class="form-control" id="reg_content" name="reg_content" placeholder="예)안녕">
							</div>
							<div class="col-sm-3">
								<button type="button" class="btn btn-success col-xs-12" id="reg_content_btn">필터링 저장</button>
							</div>
						</div>

						
						<!-- 링크 -->
						<div class="form-group">
							<label for="reg_content" class="col-sm-2 control-label">링크</label>
							<!-- <p class="lead">사용자가 말한 내용에서 필터링할 내용을 박스에 작성해주세요. 해당 필터링에 대한 텍스트를 루가 대답해줍니다.</p> -->
							<div class="col-sm-4">
								<select class="form-control" id="txt_id" name="txt_id" placeholder="예)오호~ 어서오너라!"></select>
							</div>
							<div class="col-sm-3">
								<select class="form-control" id="reg_id" name="reg_id" placeholder="예)안녕"></select>
							</div>
							<div class="col-sm-3">
								<button type="button" class="btn btn-success col-xs-12" id="link_content_btn">링크 저장</button>
							</div>
						</div>

						<!-- <div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">링크 저장</button>
							</div>
						</div> -->
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="/autoreply/resources/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/autoreply/resources/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	/* 텍스트 저장 */
	$('#txt_content_btn').on('click', function(){
		
	});
	
	
	/* 필터링 저장 */
	$('#reg_content_btn').on('click', function(){
		
	});
	
	
	/* 링크 저장 */
	$('#reg_content_btn').on('click', function(){
		
	});
	
	
	$(document).ready(function(){
		$.ajax({
			type : 'POST',
			url : '/autoreply/textlist',
			success: function(data) {
				console.log(data);
			}
		});
		/* 
		$.ajax({
			method : 'POST',
			datatype : 'json',
			url : '/autoreply/regularlist',
			success: function(data) {
				console.log(data);
			}
		});
		 */
	});
	</script>
</body>
</html>