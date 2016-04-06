(function() {
  define(['react'], function(React) {
    var EventListEntry;
    EventListEntry = React.createClass({
      getInitialState: function() {
        return {
          expanded: false
        };
      },
      gatherTicketBlocks: function() {
        var ticketBlocksApi;
        ticketBlocksApi = jsRoutes.controllers.Events.ticketBlockForEvent(this.props.event.id);
        return ticketBlocksApi.ajax().done((function(_this) {
          return function(result) {
            var availBlocks, tb;
            if (_this.isMounted()) {
              availBlocks = (function() {
                var _i, _len, _ref, _results;
                _ref = result.response;
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                  tb = _ref[_i];
                  if (tb.availability > 0) {
                    _results.push(tb);
                  }
                }
                return _results;
              })();
              return _this.setState({
                ticketBlocks: availBlocks
              });
            }
          };
        })(this)).fail((function(_this) {
          return function(jqXHR, textStatus, errorThrown) {
            var resultCode;
            resultCode = jqXHR.status;
            if (_this.isMounted()) {
              return _this.setState({
                ticketBlocks: null
              });
            }
          };
        })(this));
      },
      toggleExpanded: function() {
        if (this.state.ticketBlocks === void 0) {
          this.gatherTicketBlocks();
          this.setState({
            ticketBlocks: null
          });
        }
        return this.setState({
          expanded: !this.state.expanded
        });
      },
      placeOrder: function() {
        var customerEmail, customerName, order, ticketBlockID, ticketBlocksApi, ticketQuantity;
        ticketBlockID = this.refs.selectedTicketBlock.getDOMNode().value;
        ticketQuantity = this.refs.ticketQuantity.getDOMNode().value;
        customerName = this.refs.customerName.getDOMNode().value;
        customerEmail = this.refs.customerEmail.getDOMNode().value;
        if (customerName.length === 0) {
          alert("Your name is required");
          return;
        }
        if (customerEmail.length === 0) {
          alert("Your email is required");
          return;
        }
        order = {
          ticketBlockID: Number(ticketBlockID),
          customerName: customerName,
          customerEmail: customerEmail,
          ticketQuantity: Number(ticketQuantity)
        };
        ticketBlocksApi = jsRoutes.controllers.Orders.create();
        return ticketBlocksApi.ajax({
          data: JSON.stringify(order),
          contentType: 'application/json'
        }).done((function(_this) {
          return function(result) {
            if (_this.isMounted()) {
              alert("Order placed. REF " + result.response.id);
              return _this.setState({
                expanded: false
              });
            }
          };
        })(this)).fail((function(_this) {
          return function(jqXHR, textStatus, errorThrown) {
            var result, resultCode;
            resultCode = jqXHR.status;
            result = jqXHR.responseJSON;
            if (_this.isMounted()) {
              return alert("Error placing the order: " + result.error.message);
            }
          };
        })(this));
      },
      renderEntryBlocks: function() {
        var blockChoice, button, div, eid, input, label, option, options, select, span, _ref;
        _ref = React.DOM, div = _ref.div, span = _ref.span, option = _ref.option, label = _ref.label, select = _ref.select, input = _ref.input, button = _ref.button;
        eid = this.props.event.id;
        if (this.state.ticketBlocks != null) {
          if (this.state.ticketBlocks.length > 0) {
            options = this.state.ticketBlocks.map(function(tb) {
              var priceFormat;
              priceFormat = parseFloat(Math.round(tb.price * 100) / 100).toFixed(2);
              return option({
                key: tb.id,
                ref: "selectedTicketBlock",
                value: tb.id
              }, "" + tb.name + " - $" + priceFormat);
            });
            blockChoice = select({
              key: 'tbo',
              id: "tbo" + eid
            }, options);
            return div({
              key: 'opnl'
            }, [
              div({
                key: 'q'
              }, [
                label({
                  key: 'lt',
                  htmlFor: "tbo" + eid
                }, "Tickets:"), blockChoice, label({
                  key: 'lq',
                  htmlFor: "qty" + eid
                }, "Quantity:"), input({
                  key: 'qty',
                  ref: "ticketQuantity",
                  id: "qty" + eid,
                  type: "number",
                  max: 9999,
                  min: 1,
                  defaultValue: 1
                })
              ], div({
                key: 'n'
              }, [
                label({
                  key: 'ln',
                  htmlFor: "name" + eid
                }, "Name:"), input({
                  key: 'name',
                  ref: "customerName",
                  id: "name" + eid
                }), label({
                  key: 'le',
                  htmlFor: "email" + eid
                }, "Email:"), input({
                  key: 'email',
                  ref: "customerEmail",
                  id: "email" + eid
                }), button({
                  key: 'o',
                  onClick: this.placeOrder
                }, "Place Order")
              ]))
            ]);
          } else {
            return div({
              key: 'so'
            }, "No tickets available");
          }
        } else {
          return null;
        }
      },
      render: function() {
        var baseRow, button, contents, div, eid, eventDate, orderButton, orderText, readableDate, span, _ref;
        _ref = React.DOM, div = _ref.div, span = _ref.span, button = _ref.button;
        if (this.props.event != null) {
          eid = this.props.event.id;
          eventDate = new Date(this.props.event.start);
          readableDate = eventDate.toDateString();
          orderText = this.state.expanded ? "Cancel" : "Order";
          orderButton = button({
            key: 'o',
            onClick: this.toggleExpanded
          }, orderText);
          baseRow = div({
            key: "er-" + eid,
            className: "eventEntry"
          }, [
            span({
              key: 'evn'
            }, this.props.event.name), span({
              key: 'evc'
            }, this.props.event.city), span({
              key: 'evd'
            }, readableDate), span({
              key: 'order'
            }, orderButton)
          ]);
          contents = [baseRow];
          if (this.state.expanded) {
            contents.push(this.renderEntryBlocks());
          }
          return div({}, contents);
        } else {
          return null;
        }
      }
    });
    return EventListEntry;
  });

}).call(this);

//# sourceMappingURL=event_list_entry.js.map
