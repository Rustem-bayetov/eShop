function ProceedResult(result, nullDataMessage = "") {
    if (!result) {
        if (nullDataMessage !== "") {
            NotificationError(nullDataMessage);
        } else {
            NotificationError("Can't reach server.");
        }

        return false;
    } else {
        if (result.success) {
            return true;
        } else {
            if (result.errors) {
                result.errors.forEach(function (error) {
                    NotificationError(error);
                });
            }
            return false;
        }
    }
}

$.notifyDefaults({
    placement: {
        from: "bottom"
    },
    animate: {
        enter: "animated fadeInUp",
        exit: "animated fadeOutDown"
    }
});

function NotificationError(message) {
    $.notify({
        icon: 'fa fa-exclamation-triangle',
        title: "&nbsp;&nbsp;",
        message: message
    },
        {
            type: 'danger'
        });
}

function NotificationWarning(message) {
    $.notify({
        message: message
    },
        {
            type: 'warning'
        });
}

function NotificationInfo(message) {
    $.notify({
        message: message
    });
}

function NotificationSuccess(message) {
    $.notify({
        icon: 'fa fa-check',
        title: "&nbsp;&nbsp;",
        message: message
    },
        {
            type: 'success'
        });
}

(function ($) {
    $.fn.buttonLoader = function (action, resetIcon) {
        var self = $(this);
        //start loading animation
        if (action == 'start') {
            if ($(self).attr("disabled") == "disabled") {
                e.preventDefault();
            }
            //disable buttons when loading state
            $('.has-spinner').attr("disabled", "disabled");
            //$(self).attr('data-btn-text', $(self).text());
            //binding spinner element to button and changing button text
            $(self).html('<span class="spinner"><i class="fa fa-spinner fa-spin fa-fw"></i></span>' + $(self).text());
            //$(self).html('<span class="spinner"><i class="fa fa-spinner fa-pulse fa-fw"></i></span>Loading');
            $(self).addClass('active');
        }
        //stop loading animation
        if (action == 'stop') {
            //$(self).html($(self).attr('data-btn-text'));
            $(self).removeClass('active');
            //enable buttons after finish loading
            $('.has-spinner').removeAttr("disabled");
            if (resetIcon){
                $(self).html(`<i class="${resetIcon}"></i>` + $(self).text());
            }
        }
    }
})(jQuery);

function getOrderStatusText(status) {
    if (status == "PLACED")  return "Order placed";
    if (status == "SHIPPED") return "Item shipped";
    if (status == "CANCELLED") return "Order cancelled";
    if (status == "DELIVERED") return "Delivered";
    if (status == "RETURNED") return "Item returned";

    return status;
}
