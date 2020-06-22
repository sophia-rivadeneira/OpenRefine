/*******************************************************************************
 * Copyright (C) 2018, OpenRefine contributors
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package org.openrefine.operations.recon;
import java.io.Serializable;

import org.openrefine.RefineTest;
import org.openrefine.model.GridState;
import org.openrefine.operations.OperationRegistry;
import org.openrefine.operations.recon.ReconClearSimilarCellsOperation;
import org.openrefine.util.ParsingUtilities;
import org.openrefine.util.TestUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReconClearSimilarCellsOperationTests extends RefineTest {
	
	protected GridState initialState;
	
    @BeforeSuite
    public void registerOperation() {
        OperationRegistry.registerOperation("core", "recon-clear-similar-cells", ReconClearSimilarCellsOperation.class);
    }
    
    @Test
    public void serializeReconClearSimilarCellsOperation() throws Exception {
        String json = "{\"op\":\"core/recon-clear-similar-cells\","
                + "\"description\":\"Clear recon data for cells containing \\\"some value\\\" in column my column\","
                + "\"engineConfig\":{\"mode\":\"row-based\",\"facets\":[]},"
                + "\"columnName\":\"my column\","
                + "\"similarValue\":\"some value\"}";
        TestUtils.isSerializedTo(ParsingUtilities.mapper.readValue(json, ReconClearSimilarCellsOperation.class), json, ParsingUtilities.defaultWriter);
    }
    
    @BeforeTest
    public void setupInitialState() {
    	initialState = createGrid(
    			new String[] {"foo", "bar"},
    			new Serializable[][] {
    				{"a", new Cell("b", new Recon())},
    				{"c", new Cell("d", new Recon())}
    			});
    	
    }
}