package com.example.crud.series;

import com.example.crud.series.Series;

public interface OnItemActionListener {

    void onEdit(Series series);

    void onDelete(String id);
}
