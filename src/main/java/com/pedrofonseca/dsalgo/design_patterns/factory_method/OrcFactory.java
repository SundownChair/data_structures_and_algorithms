package com.pedrofonseca.dsalgo.design_patterns.factory_method;

import com.pedrofonseca.dsalgo.design_patterns.objects.Orc;
import com.pedrofonseca.dsalgo.design_patterns.objects.OrcEnum;

public interface OrcFactory {

    Orc createOrc(OrcEnum orcType);
}
