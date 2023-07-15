package com.example.test;

import com.example.test.model.Counter;
import com.example.test.service.CounterService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {

    public MainView(CounterService counterService) {
        TextField textField = new TextField();
        textField.setValue(String.valueOf(counterService.getLast().getCountValue()));

        Binder<Counter> binder = new Binder<>(Counter.class);
        binder.forField(textField).bind(Counter::getValue, Counter::setValue);

        Counter counter = new Counter();
        textField.addValueChangeListener(event -> {
            try {
                binder.writeBean(counter);
                counterService.addCount(counter);
            } catch (ValidationException e) {
                System.out.println(e);
            }
        });

        Button button = new Button("Increment");
        button.addClickListener(click -> {
            try {
                textField.setValue(String.valueOf(Long.parseLong(textField.getValue()) + 1));
            } catch (NumberFormatException e) {
                textField.setValue("Ошибка ввода!");
            }
        });

        add(textField);
        add(button);
    }
}