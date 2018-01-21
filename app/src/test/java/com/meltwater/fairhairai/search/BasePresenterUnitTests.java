package com.meltwater.fairhairai.search;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by thinhnguyen on 1/4/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class BasePresenterUnitTests {
    @Mock
    SearchInteractor interactor;

    @Mock
    SearchRouter router;

    @Mock
    SearchProtocol.View view;


}
