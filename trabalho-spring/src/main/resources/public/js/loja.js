$("#positivo").click(function() {
	$("#gostei").val(true);
	
	if ($(this).hasClass("btn-default")) {
		$(this).removeClass("btn-default").addClass("btn-success");
		$("#negativo").removeClass("btn-success").addClass("btn-default");
	}
});

$("#negativo").click(function() {
	$("#gostei").val(false);
	
	if ($(this).hasClass("btn-default")) {
		$(this).removeClass("btn-default").addClass("btn-success");
		$("#positivo").removeClass("btn-success").addClass("btn-default");
	}
});

$("#form").submit(function() {
	if (!$("#comentario").val() || ($("#positivo").hasClass("btn-default") && $("#negativo").hasClass("btn-default"))) {
		alert("preencha todos os campos");
		return false;
	}
	
	$("#produto").val($("#produtoId").val());
});