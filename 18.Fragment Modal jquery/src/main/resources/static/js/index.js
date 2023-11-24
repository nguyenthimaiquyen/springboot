$(document).ready(function () {
    toastr.options.timeOut = 2500; // 2.5s

    $('.create-student-btn').click(function () {
        $('#student-creation-modal').modal('show');
    });

    $('#save-student-btn').click(function () {
        //lấy dữ liệu từ form
        const formData = $('#create-student-form').serializeArray();
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
            url: "/students",
            type: "POST",
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Bạn đã tạo thành công thông tin sinh viên!");
                // $('#student-creation-modal').modal('hide');
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