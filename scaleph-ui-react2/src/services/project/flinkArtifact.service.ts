import { PageResponse, ResponseBody } from '@/typings';
import {WsArtifact, WsArtifactListParam} from './typings';
import { request } from '@umijs/max';

export const FlinkArtifactService = {
  url: '/api/flink/artifact',

  list: async (queryParam: WsArtifactListParam) => {
    return request<PageResponse<WsArtifact>>(`${FlinkArtifactService.url}`, {
      method: 'GET',
      params: queryParam,
    }).then((res) => {
      const result = {
        data: res.records,
        total: res.total,
        pageSize: res.size,
        current: res.current,
      };
      return result;
    });
  },

  add: async (row: WsArtifact) => {
    return request<ResponseBody<any>>(`${FlinkArtifactService.url}`, {
      method: 'PUT',
      data: row,
    });
  },

  update: async (row: WsArtifact) => {
    return request<ResponseBody<any>>(`${FlinkArtifactService.url}`, {
      method: 'POST',
      data: row,
    });
  },

  deleteOne: async (row: WsArtifact) => {
    return request<ResponseBody<any>>(`${FlinkArtifactService.url}/` + row.id, {
      method: 'DELETE',
    });
  },
};
