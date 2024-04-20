/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2024 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ext.databend.model;

import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.ext.generic.model.*;
import org.jkiss.dbeaver.ext.generic.model.meta.GenericMetaModel;
import org.jkiss.dbeaver.model.DBUtils;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCUtils;
import org.jkiss.dbeaver.model.impl.jdbc.cache.JDBCBasicDataTypeCache;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.sql.format.SQLFormatUtils;
import org.jkiss.utils.CommonUtils;

import java.sql.SQLException;
import java.util.Map;

public class DatabendMetaModel extends GenericMetaModel {

	private static final Log log = Log.getLog(DatabendMetaModel.class);
	private static final String SHOW_TABLE_DDL = "SHOW CREATE TABLE ";
	private static final String SHOW_VIEW_DDL = "SHOW CREATE VIEW ";

	public DatabendMetaModel() {
		super();
	}

	@Override
	public String getTableDDL(@NotNull DBRProgressMonitor monitor, @NotNull GenericTableBase sourceObject,
			@NotNull Map<String, Object> options) throws DBException {
		String objectDDL = getObjectDDL(monitor, sourceObject, options, SHOW_TABLE_DDL);
		if (objectDDL == null) {
			return super.getTableDDL(monitor, sourceObject, options);
		}
		return objectDDL;
	}

	@Override
	public String getViewDDL(@NotNull DBRProgressMonitor monitor, @NotNull GenericView sourceObject,
			@NotNull Map<String, Object> options) throws DBException {
		String objectDDL = getObjectDDL(monitor, sourceObject, options, SHOW_VIEW_DDL);
		if (objectDDL == null) {
			return "-- Databend view definition not found";
		}
		return objectDDL;
	}

	@Nullable
	private String getObjectDDL(@NotNull DBRProgressMonitor monitor, @NotNull GenericTableBase sourceObject,
			@NotNull String sysViewName, @NotNull String ddlTpye) throws DBException {
		GenericDataSource dataSource = sourceObject.getDataSource();
		try (JDBCSession session = DBUtils.openMetaSession(monitor, sourceObject, "Read Databend view/table source")) {
			try (JDBCPreparedStatement dbStat = session
					.prepareStatement(ddlTpye + sourceObject.getFullyQualifiedName(DBPEvaluationContext.DDL))) {

				try (JDBCResultSet dbResult = dbStat.executeQuery()) {
					StringBuilder sql = new StringBuilder();
					while (dbResult.next()) {
						String line = dbResult.getString(1);
						if (line == null) {
							continue;
						}
						sql.append(line).append("\n");
					}
					return sql.toString();
				}
			}
		} catch (SQLException e) {
			log.warn("Can't read object " + sourceObject.getName() + " definition from the database", e);
			return null;
		}
	}
}