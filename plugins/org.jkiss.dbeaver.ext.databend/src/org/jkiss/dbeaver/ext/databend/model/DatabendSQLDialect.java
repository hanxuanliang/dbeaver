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

import org.jkiss.dbeaver.ext.generic.model.GenericSQLDialect;

public class DatabendSQLDialect extends GenericSQLDialect {

	private static final String[] DATABEND_FUNCTIONS = { "abs", "acos", "add", "add_days", "add_hours", "add_minutes",
			"add_months", "add_quarters", "add_seconds", "add_years", "ai_embedding_vector", "ai_text_completion",
			"and", "and_filters", "array", "array_aggregate", "array_any", "array_append",
			"array_approx_count_distinct", "array_avg", "array_concat", "array_contains", "array_count",
			"array_distinct", "array_flatten", "array_get", "array_indexof", "array_kurtosis", "array_length",
			"array_max", "array_median", "array_min", "array_prepend", "array_remove_first", "array_remove_last",
			"array_skewness", "array_slice", "array_sort", "array_sort_asc_null_first", "array_sort_asc_null_last",
			"array_sort_desc_null_first", "array_sort_desc_null_last", "array_std", "array_stddev", "array_stddev_pop",
			"array_stddev_samp", "array_sum", "array_to_string", "array_unique", "as_array", "as_boolean", "as_float",
			"as_integer", "as_object", "as_string", "ascii", "asin", "assume_not_null", "atan", "atan2", "bin",
			"bit_and", "bit_length", "bit_not", "bit_or", "bit_shift_left", "bit_shift_right", "bit_xor", "bitmap_and",
			"bitmap_and_not", "bitmap_cardinality", "bitmap_contains", "bitmap_count", "bitmap_has_all",
			"bitmap_has_any", "bitmap_max", "bitmap_min", "bitmap_not", "bitmap_or", "bitmap_subset_in_range",
			"bitmap_subset_limit", "bitmap_xor", "blake3", "build_bitmap", "cast", "cbrt", "ceil", "ceiling", "char",
			"char_length", "character_length", "check_json", "city64withseed", "coalesce", "concat", "concat_ws",
			"connection_id", "contains", "cos", "cosine_distance", "cot", "crc32", "current_database", "current_role",
			"current_timestamp", "current_user", "currentdatabase", "currentuser", "database", "date", "date_add",
			"date_format", "date_part", "date_sub", "date_trunc", "day", "dayofmonth", "dayofyear", "degrees",
			"delete_by_keypath", "div", "div0", "divide", "divnull", "eq", "error_or", "exp", "extract", "factorial",
			"flatten", "floor", "from_base64", "from_hex", "gen_random_uuid", "geo_distance", "geo_to_h3",
			"geohash_decode", "geohash_encode", "get", "get_by_keypath", "get_by_keypath_string", "get_ignore_case",
			"get_path", "get_string", "great_circle_angle", "great_circle_distance", "greatest", "grouping", "gt",
			"gte", "h3_cell_area_m2", "h3_cell_area_rads2", "h3_distance", "h3_edge_angle", "h3_edge_length_km",
			"h3_edge_length_m", "h3_exact_edge_length_km", "h3_exact_edge_length_m", "h3_exact_edge_length_rads",
			"h3_get_base_cell", "h3_get_destination_index_from_unidirectional_edge", "h3_get_faces",
			"h3_get_indexes_from_unidirectional_edge", "h3_get_origin_index_from_unidirectional_edge",
			"h3_get_resolution", "h3_get_unidirectional_edge", "h3_get_unidirectional_edge_boundary",
			"h3_get_unidirectional_edges_from_hexagon", "h3_hex_area_km2", "h3_hex_area_m2", "h3_hex_ring",
			"h3_indexes_are_neighbors", "h3_is_pentagon", "h3_is_res_class_iii", "h3_is_valid", "h3_k_ring", "h3_line",
			"h3_num_hexagons", "h3_to_center_child", "h3_to_children", "h3_to_geo", "h3_to_geo_boundary",
			"h3_to_parent", "h3_to_string", "h3_unidirectional_edge_is_valid", "hex", "humanize_number",
			"humanize_size", "if", "ifnull", "ignore", "inet_aton", "inet_ntoa", "insert", "instr", "intdiv",
			"ipv4_num_to_string", "ipv4_string_to_num", "is_array", "is_boolean", "is_error", "is_float", "is_integer",
			"is_not_error", "is_not_null", "is_null", "is_null_value", "is_object", "is_string", "is_true",
			"json_array", "json_array_elements", "json_contains_in_left", "json_contains_in_right", "json_each",
			"json_exists_all_keys", "json_exists_any_keys", "json_exists_key", "json_extract_path_text", "json_object",
			"json_object_keep_null", "json_object_keys", "json_path_exists", "json_path_match", "json_path_query",
			"json_path_query_array", "json_path_query_first", "json_pretty", "json_strip_nulls", "json_to_string",
			"json_typeof", "l2_distance", "last_query_id", "lcase", "least", "left", "length", "length_utf8", "like",
			"ln", "locate", "log", "log10", "log2", "lower", "lpad", "lt", "lte", "ltrim", "map", "md5", "mid", "minus",
			"mod", "modulo", "month", "months_between", "multiply", "neg", "negate", "not", "noteq", "now", "nullif",
			"nvl", "nvl2", "object_keys", "oct", "octet_length", "or", "ord", "parse_json", "pi", "plus",
			"point_in_ellipses", "point_in_polygon", "position", "position", "pow", "power", "quarter", "quote",
			"radians", "rand", "range", "regexp", "regexp_instr", "regexp_like", "regexp_replace", "regexp_substr",
			"remove_nullable", "repeat", "replace", "reverse", "right", "rlike", "round", "rpad", "rtrim",
			"running_difference", "sha", "sha1", "sha2", "sign", "sin", "siphash", "siphash64", "sleep", "slice",
			"soundex", "space", "split", "split_part", "sqrt", "st_geom_point", "st_geometryfromewkb",
			"st_geometryfromewkt", "st_geometryfromtext", "st_geometryfromwkb", "st_geometryfromwkt", "st_geomfromewkb",
			"st_geomfromewkt", "st_geomfromgeohash", "st_geomfromtext", "st_geomfromwkb", "st_geomfromwkt",
			"st_make_line", "st_makegeompoint", "st_makeline", "str_to_date", "str_to_timestamp", "str_to_year",
			"strcmp", "stream_has_data", "string_to_h3", "sub_bitmap", "substr", "substr_utf8", "substring",
			"substring", "substring_utf8", "subtract", "subtract_days", "subtract_hours", "subtract_minutes",
			"subtract_months", "subtract_quarters", "subtract_seconds", "subtract_years", "tan", "time_slot",
			"timezone", "to_base64", "to_binary", "to_bitmap", "to_boolean", "to_date", "to_datetime",
			"to_day_of_month", "to_day_of_week", "to_day_of_year", "to_decimal", "to_float32", "to_float64",
			"to_geometry", "to_hex", "to_hour", "to_int16", "to_int32", "to_int64", "to_int8", "to_minute", "to_monday",
			"to_month", "to_nullable", "to_quarter", "to_second", "to_start_of_day", "to_start_of_fifteen_minutes",
			"to_start_of_five_minutes", "to_start_of_hour", "to_start_of_iso_year", "to_start_of_minute",
			"to_start_of_month", "to_start_of_quarter", "to_start_of_second", "to_start_of_ten_minutes",
			"to_start_of_week", "to_start_of_year", "to_string", "to_text", "to_timestamp", "to_uint16", "to_uint32",
			"to_uint64", "to_uint8", "to_unix_timestamp", "to_uuid", "to_varchar", "to_variant", "to_variant",
			"to_week_of_year", "to_year", "to_yyyymm", "to_yyyymmdd", "to_yyyymmddhh", "to_yyyymmddhhmmss", "today",
			"tomorrow", "translate", "trim", "trim", "trim_both", "trim_leading", "trim_trailing", "truncate",
			"try_cast", "try_from_base64", "try_from_hex", "try_inet_aton", "try_inet_ntoa", "try_ipv4_num_to_string",
			"try_ipv4_string_to_num", "try_json_object", "try_json_object_keep_null", "try_parse_json", "try_to_binary",
			"try_to_boolean", "try_to_date", "try_to_datetime", "try_to_decimal", "try_to_float32", "try_to_float64",
			"try_to_int16", "try_to_int32", "try_to_int64", "try_to_int8", "try_to_string", "try_to_timestamp",
			"try_to_uint16", "try_to_uint32", "try_to_uint64", "try_to_uint8", "try_to_variant", "try_to_variant",
			"tuple", "typeof", "ucase", "unhex", "unnest", "upper", "user", "uuid", "version", "week", "weekofyear",
			"xor", "xxhash32", "xxhash64", "year", "yesterday", "min", "quantile_tdigest", "median_tdigest_weighted",
			"covar_samp", "window_funnel", "uniq", "bitmap_or_count", "bitmap_intersect", "quantile", "histogram",
			"stddev_samp", "median", "std", "stddev_pop", "approx_count_distinct", "list", "group_array_moving_avg",
			"covar_pop", "median_tdigest", "group_array_moving_sum", "bitmap_union", "quantile_cont", "string_agg",
			"count", "any", "intersect_count", "quantile_disc", "arg_min", "retention", "bitmap_and_count", "kurtosis",
			"stddev", "array_agg", "sum", "bitmap_xor_count", "max", "arg_max", "quantile_tdigest_weighted", "skewness",
			"bitmap_not_count", "avg" };

	private static final String[] DATABEND_NONKEYWORDS = { "DEFAULT", "SYSTEM" };

	public DatabendSQLDialect() {
		super("Databend SQL", "databend");
	}
}
