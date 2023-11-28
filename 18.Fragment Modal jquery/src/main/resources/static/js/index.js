$(document).ready(function () {
    toastr.options.timeOut = 2500; // 2.5s

    let deleteStudentId = -1;
    let updateStudentId = -1;

    //mở modal tạo mới sinh viên
    $('.create-student-btn').click(function () {
        $('#student-creation-modal').modal('show');
    });

    //save new student
    $('#save-student-btn').click(function () {
        //lấy dữ liệu từ form
        const formData = $('#create-student-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        console.log(formData)
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }
        //call api lên backend
        $.ajax({
            url: "/students",
            type: "POST",
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Bạn đã tạo thành công thông tin sinh viên!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //open modal delete student
    $('.delete-student-btn').click(function (event) {
        deleteStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $('#student-delete-modal').modal('show');
    });

    //delete student
    $('#delete-student-btn').click(function () {
        $.ajax({
            url: "/students/" + deleteStudentId,
            type: "DELETE",
            success: function (data) {
                toastr.success("Bạn đã xóa thành công thông tin sinh viên!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //open modal update student
    $('.update-student-modal-open').click(function (event) {
        //call api lên java và lấy dữ liệu
        updateStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $.ajax({
            url: "/students/" + updateStudentId,
            type: "GET",
            success: function (data) {
                //đổ dữ liệu vào form
                $('#update-student-form #name').val(data.name);
                $('#update-student-form #address').val(data.address);
                $('#update-student-form #phone').val(data.phone);
                $('#update-student-form #className').val(data.className);

                $('#student-update-modal').modal("show");
            },
            error: function (err) {
                console.error(err);
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //update student
    $('#update-student-btn').click(function () {
        //lấy dữ liệu từ form
        const formData = $('#update-student-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }
        //call api lên backend
        $.ajax({
            url: "/students/" + updateStudentId,
            type: "PUT",
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Update a student successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

});